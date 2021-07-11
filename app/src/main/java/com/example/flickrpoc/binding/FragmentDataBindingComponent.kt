
package com.example.flickrpoc.binding

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment
import com.example.flickrpoc.binding.FragmentBindingAdapters

/**
 * A Data Binding Component implementation for fragments.
 */
class FragmentDataBindingComponent(fragment: Fragment) : DataBindingComponent {
    private val adapter = FragmentBindingAdapters(fragment)

    override fun getFragmentBindingAdapters() = adapter
}
