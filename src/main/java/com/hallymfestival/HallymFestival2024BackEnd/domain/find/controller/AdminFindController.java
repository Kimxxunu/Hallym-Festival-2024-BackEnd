package com.hallymfestival.HallymFestival2024BackEnd.domain.find.controller;
import com.hallymfestival.HallymFestival2024BackEnd.domain.find.dto.FindAddRequest;
import com.hallymfestival.HallymFestival2024BackEnd.domain.find.dto.FindApiResponse;
import com.hallymfestival.HallymFestival2024BackEnd.domain.find.service.FindServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/find")
public class AdminFindController {

    private final FindServiceImpl findService;
    //분실물 등록
    @PostMapping("/create")
    @CrossOrigin(origins = "https://hallym-festival-admin.com", maxAge = 3600)
    public ResponseEntity<String> addFind(@RequestParam("image") MultipartFile image,
                                          @RequestParam("name") String name,
                                          @RequestParam("location") String location) throws IOException {
        FindAddRequest findAddRequest = new FindAddRequest();
        findAddRequest.setImage(image);
        findAddRequest.setName(name);
        findAddRequest.setLocation(location);
        FindApiResponse createFind = findService.addFind(findAddRequest);
        return ResponseEntity.ok("분실물 등록 완료");
    }

    @GetMapping
    @CrossOrigin(origins = "https://hallym-festival-admin.com", maxAge = 3600)
    public ResponseEntity<List<FindApiResponse>> getBoardList() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // 토큰이 있음을 로그로 찍음
            System.out.println("토큰이 존재함!");
        } else {
            // 토큰이 없음을 로그로 찍음
            System.out.println("토큰이 존재하지 않음!");
        }

        List<FindApiResponse> findList = findService.getList();
        return ResponseEntity.ok().body(findList);
    }

    //분실물 삭제
    @DeleteMapping("{id}")
    @CrossOrigin(origins = "https://hallym-festival-admin.com", maxAge = 3600)
    public ResponseEntity<String> deleteFind(@PathVariable long id) {
        try {
            findService.deleteFind(id);
            return ResponseEntity.ok("삭제완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("삭제 실패");
        }
    }
}