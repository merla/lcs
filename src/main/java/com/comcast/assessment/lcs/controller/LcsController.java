package com.comcast.assessment.lcs.controller;

import com.comcast.assessment.lcs.model.LcsRequest;
import com.comcast.assessment.lcs.model.LcsResponse;
import com.comcast.assessment.lcs.service.LcsSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lcs")
public class LcsController {
    @Autowired
    private LcsSvc lcsSvc;

    @PostMapping
    public ResponseEntity<LcsResponse> lcs(@RequestBody LcsRequest lcsReqDto) {
        ResponseEntity<LcsResponse> validationResp = lcsSvc.validateRequest(lcsReqDto);
        if (null != validationResp)
            return validationResp;
        return new ResponseEntity<LcsResponse>(lcsSvc.getLongestSubstring(lcsReqDto), HttpStatus.OK);
    }
}
