package com.example.bottleshop_app

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bottleshop_app.databinding.FragmentMapBinding
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView


class MapFragment : Fragment() {
    private lateinit var binding: FragmentMapBinding

    private var mapView: KakaoMap? = null
    private val startZoomLevel = 15

    // TODO(위도와 경도 HomeFragment 에서 가져오기)
    val locationProvider = LocationProvider(requireContext())
    var currentLat = locationProvider.getLocationLatitude()
    var currentLng = locationProvider.getLocationLongitude()

    // MapLifeCycleCallback 을 통해 지도의 LifeCycle 관련 이벤트를 수신
    private val lifeCycleCallback: MapLifeCycleCallback = object : MapLifeCycleCallback() {
        override fun onMapDestroy() {
            // 지도 API 가 정상적으로 종료될 때 호출됨
        }

        override fun onMapError(error: Exception) {  // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
            Log.e("k3f", "onMapError: ${error.message}")
            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
        }
    }

    // MapReadyCallback 을 통해 지도가 정상적으로 시작된 후에 수신
    private val readyCallback: KakaoMapReadyCallback = object : KakaoMapReadyCallback() {
        override fun onMapReady(kakaoMap: KakaoMap) {  // 인증 후 API 가 정상적으로 실행될 때 호출
            binding.btnCheckHere.setOnClickListener {
                Log.i("k3f", "startPosition: ${kakaoMap.cameraPosition!!.position.toString()}")
                Log.i("k3f", "startZoomLevel: ${kakaoMap.zoomLevel}")

                mapView.let {
                    /*val intent = Intent()
                    // 버튼이 눌린 시점의 카메라 포지션 가져옴(보이는 지도의 중앙지점 좌푯값 가져옴)
                    intent.putExtra("latitude", it.cameraPosition.target.latitude)
                    intent.putExtra("longitude", it.cameraPosition.target.longitude)*/
                    // TODO(현 지도 내에서 바틀샵 검색 후 도출 코드 작성)
                }
            }
        }

        override fun getPosition(): LatLng {  // 지도 시작 시 위치 좌표를 설정
            return LatLng.from(currentLat, currentLng)
        }

        override fun getZoomLevel(): Int {  // 지도 시작 시 확대/축소 줌 레벨 설정
            return startZoomLevel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(layoutInflater)

        val mapView: MapView = binding.mapViewWhole
        mapView.start(lifeCycleCallback, readyCallback)

        return binding.root
    }

}