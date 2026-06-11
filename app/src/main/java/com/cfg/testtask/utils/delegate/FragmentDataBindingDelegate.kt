package com.cfg.testtask.utils.delegate

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentDataBindingDelegate<T : ViewDataBinding>(
    private val fragment: Fragment,
) : ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            val viewLifecycleOwnerObserver = Observer<LifecycleOwner?> { owner ->
                if (owner == null) {
                    binding = null
                }
            }

            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observeForever(viewLifecycleOwnerObserver)
            }

            override fun onDestroy(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.removeObserver(viewLifecycleOwnerObserver)
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = binding

        if (binding != null && binding.root === thisRef.view) {
            return binding
        }

        val view = thisRef.view
            ?: throw IllegalStateException("Should not attempt to get bindings when the Fragment's view is null.")

        return requireNotNull(DataBindingUtil.bind<T>(view)) {
            "DataBinding was not generated for ${view.resources.getResourceEntryName(view.id)}."
        }.also { this.binding = it }
    }
}

fun <T : ViewDataBinding> Fragment.dataBinding() =
    FragmentDataBindingDelegate<T>(this)
