
package com.comcast.assessment.lcs.service;


import java.util.ArrayList;
import java.util.List;

import com.comcast.assessment.lcs.model.LcsRequest;
import com.comcast.assessment.lcs.model.LcsResponse;
import com.comcast.assessment.lcs.model.StringWithValue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class LcsSvcImplTest {
    private LcsSvcImpl lcsSvcImpl;

    @Before
    public void setup() {
        lcsSvcImpl = new LcsSvcImpl();
    }

    @Test
    public void testNullRequest() {
        Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, lcsSvcImpl.validateRequest(null).getStatusCode());
    }

    @Test
    public void testEmptyInputList() {
        Assert.assertEquals(HttpStatus.BAD_REQUEST, lcsSvcImpl.validateRequest(new LcsRequest()).getStatusCode());
    }

    @Test
    public void testDuplicateInList() {
        LcsRequest req = new LcsRequest();
        List<StringWithValue> list = new ArrayList<>();
        StringWithValue stirngWithValue = new StringWithValue();
        stirngWithValue.setValue("broadcaster");
        list.add(stirngWithValue);
        StringWithValue stirngWithValue1 = new StringWithValue();
        stirngWithValue1.setValue("broadcaster");
        list.add(stirngWithValue1);
        req.setSetOfStrings(list);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, lcsSvcImpl.validateRequest(req).getStatusCode());
    }

    @Test
    public void testLcs() {
        LcsRequest lcsReq = new LcsRequest();
        List<StringWithValue> list = new ArrayList<>();
        StringWithValue stirngWithValue = new StringWithValue();
        stirngWithValue.setValue("comcastghe");
        list.add(stirngWithValue);
        StringWithValue stirngWithValue1 = new StringWithValue();
        stirngWithValue1.setValue("communicateghe");
        list.add(stirngWithValue1);
        StringWithValue stirngWithValue2 = new StringWithValue();
        stirngWithValue2.setValue("commutationghe");
        list.add(stirngWithValue2);
        lcsReq.setSetOfStrings(list);
        LcsResponse lcsResp = lcsSvcImpl.getLongestSubstring(lcsReq);
        Assert.assertEquals(2, lcsResp.getLcs().size());
    }
}

