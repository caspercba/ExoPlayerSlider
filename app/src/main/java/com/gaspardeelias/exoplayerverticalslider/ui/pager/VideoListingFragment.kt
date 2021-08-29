package com.gaspardeelias.exoplayerverticalslider.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.gaspardeelias.exoplayerverticalslider.MainApplication
import com.gaspardeelias.exoplayerverticalslider.databinding.FragmentVideoListingBinding
import com.gaspardeelias.exoplayerverticalslider.ui.VideoListingViewModel
import com.gaspardeelias.repo.Repository

class VideoListingFragment : Fragment() {

    private val vm: VideoListingViewModel by viewModels {
        VideoListingViewModel.ViewModelFactory(
            repo
        )
    }

    private lateinit var binding: FragmentVideoListingBinding
    private lateinit var adapter: VideoFragmentStateAdapter
    private lateinit var repo: Repository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoListingBinding.inflate(inflater, container, false)
        repo = (requireActivity().application as MainApplication).getRepository()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = VideoFragmentStateAdapter(this)
        binding.pager.adapter = adapter
        binding.pager.orientation = ViewPager2.ORIENTATION_VERTICAL

        vm.data.observe(viewLifecycleOwner) {
            adapter.setVideos(it)
        }
    }

    override fun onResume() {
        super.onResume()
        vm.refresh()
    }

}
