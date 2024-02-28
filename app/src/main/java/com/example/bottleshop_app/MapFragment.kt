package com.example.bottleshop_app

import android.R
import android.content.Context
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

    private lateinit var mActivity: MainActivity

    private var mapView: KakaoMap? = null
    private val startZoomLevel = 15
    private lateinit var startPosition: LatLng

    // MapLifeCycleCallback 을 통해 지도의 LifeCycle 관련 이벤트를 수신
    private val lifeCycleCallback: MapLifeCycleCallback = object : MapLifeCycleCallback() {
        override fun onMapDestroy() {
            // 지도 API 가 정상적으로 종료될 때 호출됨
        }

        override fun onMapError(error: Exception) {  // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
            Log.e("k3f", "onMapError: ${error.message}")
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
            return startPosition
        }

        override fun getZoomLevel(): Int {  // 지도 시작 시 확대/축소 줌 레벨 설정
            return startZoomLevel
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(layoutInflater)

        val locationProvider = LocationProvider(mActivity)
        val uLatitude = locationProvider.getLocationLatitude()
        val uLongtitude = locationProvider.getLocationLongitude()
        Log.d("MyTag", "Latitude: $uLatitude, Longitude: $uLongtitude")

        // 원래 startPosition = LatLng.from(uLatitude, uLongtitude) 코드 이지만,
        // 현재 애뮬레이터라 현위치 받아오기가 불가능해서 판교역으로 처음 위치 설정 => 실제 핸드폰으로 실행 시 위의 코드로 바꿔주면 현위치로 잡힘
        startPosition = LatLng.from(37.394660,127.111182)

        val mapView: MapView = binding.mapViewWhole
        mapView.start(lifeCycleCallback, readyCallback)

        return binding.root
    }

}