package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/api/reservations")
public class ReservationAPIController {

    @PostMapping("")
    public Map<String, Object> reservation(@RequestBody Map<String, Object> reservationData) {
        Map<String, Object> map = new HashMap<>();

        //TODO : 예약에 성공했을 경우
        if(false){
            map.put("reservationId", 1001);
            map.put("message", "예약이 완료되었습니다.");
        }
        //TODO : 예약에 실패했을 경우
        else {
            map.put("error","해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요.");
        }
        return map;
    }


    @DeleteMapping("/{id}")
    public Map<String, Object> cancelReservation(@RequestBody Map<String, Object> reservationData, @PathVariable Long id) {
        String cancelReason = reservationData.get("cancelReason").toString();
        Map<String, Object> map = new HashMap<>();

        // TODO : 예약 취소 성공
        if(true){
            map.put("message", "예약이 취소되었습니다.");
        }

        // TODO : 예약이 존재하지 않는 경우
        else{
            map.put("error", "존재하지 않는 예약입니다.");
        }
        return map;
    }
}
