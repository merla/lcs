package com.comcast.assessment.lcs.service;

import com.comcast.assessment.lcs.model.LcsRequest;
import com.comcast.assessment.lcs.model.LcsResponse;
import com.comcast.assessment.lcs.model.StringWithValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class LcsSvcImpl implements LcsSvc {

    @Override
    public ResponseEntity<LcsResponse> validateRequest(LcsRequest request) {
        ResponseEntity<LcsResponse> lcsResponseEntity = null;
        LcsResponse resBody = new LcsResponse();

        if (isNullRequest(request)) {
            resBody.setError("Incorrect input request");
            lcsResponseEntity = new ResponseEntity<LcsResponse>(resBody, HttpStatus.NOT_ACCEPTABLE);
        } else if (isEmptyList(request)) {
            resBody.setError("setOfStrings should not be empty");
            lcsResponseEntity = new ResponseEntity<LcsResponse>(resBody, HttpStatus.BAD_REQUEST);
        } else if (isDuplicatesInList(request)) {
            resBody.setError("setOfStrings must be a Set");
            lcsResponseEntity = new ResponseEntity<LcsResponse>(resBody, HttpStatus.BAD_REQUEST);
        }

        return lcsResponseEntity;
    }


    private boolean isDuplicatesInList(LcsRequest lcsReqDto) {
        List<StringWithValue> setOfStrings = lcsReqDto.getSetOfStrings();
        List<String> listOfStringss = new ArrayList<>();
        Set<String> setOfStringss = new HashSet<>();
        setOfStrings.forEach(strWithValue -> {
            listOfStringss.add(strWithValue.getValue());
            setOfStringss.add(strWithValue.getValue());
        });
        return listOfStringss.size() != setOfStringss.size();
    }

    @Override
    public LcsResponse getLongestSubstring(LcsRequest lcsRequest) {
        LcsResponse lcsResponse = new LcsResponse();
        List<StringWithValue> res = new ArrayList<>();
        lcsResponse.setLcs(res);
        List<String> longestSubStrings = findLongestSubString(lcsRequest.getSetOfStrings());
        sortListInDescByStrLength(longestSubStrings);
        String longestSubstr = longestSubStrings.get(0);
        int longestStrLength = longestSubstr.length();
        addSubStrToList(longestSubstr, res);
        longestSubStrings.remove(0);
        for (String subStr: longestSubStrings) {
            if (subStr.length() == longestStrLength)
                addSubStrToList(subStr, res);
        }
        return lcsResponse;
    }

    private void sortListInDescByStrLength(List<String> longestSubStrings) {
        Collections.sort(longestSubStrings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.length() - s1.length();
            }
        });
    }

    private void addSubStrToList(String substr, List<StringWithValue> strWithValueList) {
        StringWithValue str = new StringWithValue();
        str.setValue(substr);
        strWithValueList.add(str);
    }

    private boolean isEmptyList(LcsRequest lcsReq) {
        return lcsReq.getSetOfStrings().isEmpty();
    }

    private boolean isNullRequest(LcsRequest lcsReq) {
        return null == lcsReq;
    }

    private List<String> findLongestSubString(List<StringWithValue> inputValues) {
        List<String> respValues = new ArrayList<>();
        sortInDescOrder(inputValues);
        String longestString = inputValues.get(0).getValue();
        inputValues.remove(0);

        for (int i = 0; i <= longestString.length(); i++) {
            for (int j = i; j <= longestString.length(); j++) {
                String subStr = longestString.substring(i, j);
                if (checkIfValidSubstr(subStr, inputValues) && !respValues.contains(subStr)) {
                    respValues.add(subStr);
                }
            }
        }
        return respValues;
    }

    private void sortInDescOrder(List<StringWithValue> inputValues) {
        Collections.sort(inputValues, new Comparator<StringWithValue>() {
            @Override
            public int compare(StringWithValue s1, StringWithValue s2) {
                return s2.getValue().length() - s1.getValue().length();
            }
        });
    }

    private boolean checkIfValidSubstr(String subStr, List<StringWithValue> values) {
        AtomicBoolean valid = new AtomicBoolean(true);
        values.forEach(value -> {
            if (!value.getValue().contains(subStr))
                valid.set(false);
            ;
        });

        return valid.get();
    }
}
