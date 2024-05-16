package com.hallymfestival.HallymFestival2024BackEnd.domain.reservation.controller;


import com.hallymfestival.HallymFestival2024BackEnd.domain.reservation.dto.ReservationRequestDto;
import com.hallymfestival.HallymFestival2024BackEnd.domain.reservation.dto.ReservationSaveDto;
import com.hallymfestival.HallymFestival2024BackEnd.domain.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api")
public class ReservationController {

    private final ReservationService reservationService;


    @GetMapping("/reservationdetail")
    public ResponseEntity<Integer> getReservationTotalCount(){
        int totalCount =reservationService.getReservationTotalCount();
        return ResponseEntity.ok().body(totalCount);
    }


    @PostMapping("/reservationinfo")
    public ResponseEntity<ReservationRequestDto> getReservationInfo(@RequestBody ReservationRequestDto requestDto) {
        ReservationRequestDto reservationInfo;
        reservationInfo = reservationService.getReservationInfo(requestDto.getStudentId(), requestDto.getName());
        return ResponseEntity.ok().body(reservationInfo);
    }


//    @PostMapping("/reservation")
//    public ResponseEntity<ReservationEntity> insertReservation(@RequestBody ReservationSaveDto reservationSaveDto){
//        ReservationEntity newReservation = reservationService.insertReservation(reservationSaveDto);
//
//        if (newReservation == null) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        return ResponseEntity.ok().body(newReservation);
//    }

//    @PostMapping("/reservationdetail")
//    public void insertReservation(@RequestBody ReservationSaveDto reservationSaveDto) {
//        reservationService.insertReservation(reservationSaveDto);
//    }

    @PostMapping("/reservationdetail")
    public ResponseEntity<Boolean> insertReservation(@RequestBody ReservationSaveDto reservationSaveDto) {
        boolean result = reservationService.insertReservation(reservationSaveDto);
        Boolean resultObject = Boolean.valueOf(result); // Convert primitive boolean to Boolean object

        if (result) {
            return ResponseEntity.ok(resultObject);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultObject);
        }

    }

}