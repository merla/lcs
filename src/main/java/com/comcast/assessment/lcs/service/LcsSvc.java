package com.comcast.assessment.lcs.service;

import com.comcast.assessment.lcs.model.LcsRequest;
import com.comcast.assessment.lcs.model.LcsResponse;
import org.springframework.http.ResponseEntity;

public interface LcsSvc {

	ResponseEntity<LcsResponse> validateRequest(LcsRequest request);

	LcsResponse getLongestSubstring(LcsRequest request);
}
