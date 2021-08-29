package com.gaspardeelias.exoplayerverticalslider.ui.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.gaspardeelias.exoplayerverticalslider.MainApplication
import com.gaspardeelias.exoplayerverticalslider.databinding.FragmentVideoRecyclerBinding
import com.gaspardeelias.exoplayerverticalslider.ui.VideoListingViewModel
import com.gaspardeelias.repo.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class VideoRecyclerFragment : Fragment() {

    val vm: VideoListingViewModel by viewModels { VideoListingViewModel.ViewModelFactory(repo) }

    lateinit var binding: FragmentVideoRecyclerBinding
    lateinit var videoAdapter: VideoRecyclerAdapter
    lateinit var repo: Repository
    val eventBus = CustomEventBus()

    init {
        CoroutineScope(Dispatchers.Main).launch {
            eventBus.events.filter { it is RecyclerEvent.ItemVisible }.collectLatest { event -> eventBusEvent(event as RecyclerEvent.ItemVisible) }
        }
    }

    suspend fun eventBusEvent(event: RecyclerEvent.ItemVisible) {
        val view = (binding.videoRecycler.layoutManager as LinearLayoutManager).findViewByPosition(event.pos)
        eventBus.invokeEvent(RecyclerEvent.AttachPlayer(view, event.pos))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoRecyclerBinding.inflate(inflater, container, false)
        repo = (requireActivity().application as MainApplication).getRepository()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoAdapter = VideoRecyclerAdapter(requireContext(), eventBus)
        binding.videoRecycler.also {
            it.setItemViewCacheSize(0)
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = videoAdapter
            val helper: SnapHelper = CustomSnapHelper(eventBus)
            helper.attachToRecyclerView(it)
        }
        vm.data.observe(viewLifecycleOwner) {
            videoAdapter.setVideos(it)
        }
    }

    override fun onResume() {
        super.onResume()
        vm.refresh()
    }
}