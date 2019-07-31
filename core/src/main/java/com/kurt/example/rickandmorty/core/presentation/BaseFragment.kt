package com.kurt.example.rickandmorty.core.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel


/**
 * Base Class of Fragments for handling Fragment Lifecycle Functions
 *
 * @author Kurt Renzo Acosta
 * @since 10/8/17 <version>
 */
abstract class BaseFragment<T : ViewModel> : Fragment() {
    abstract val viewModel: T
    abstract val layout: Int

    open fun setupPage(view: View) {}
    open fun subscribeToInputs(view: View) {}
    open fun subscribeToOutputs(view: View) {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPage(view)
        subscribeToInputs(view)
        subscribeToOutputs(view)
    }
}
