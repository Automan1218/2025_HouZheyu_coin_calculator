package com.example.core;
import com.example.api.CoinApi;
import jakarta.ws.rs.core.Response;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CoinServiceTest {
    @Test
    public void testCalculateSuccess() {
        // 输入测试数据
        double targetAmount = 7.03;
        List<Double> denominations = Arrays.asList(0.01,0.5,1.0,5.0,10.0);

        // 期望的输出
        List<Double> expected = Arrays.asList(0.01, 0.01, 0.01, 1.0, 1.0, 5.0);

        // 调用方法并验证
        List<Double> result = CoinService.calculate(targetAmount, denominations);
        assertEquals(expected, result);
    }

    @Test
    public void testCalculateExactAmount() {
        double targetAmount = 103.0;
        List<Double> denominations = Arrays.asList(1.0, 2.0 , 50.0);

        List<Double> expected = Arrays.asList(1.0, 2.0, 50.0, 50.0);

        List<Double> result = CoinService.calculate(targetAmount, denominations);
        assertEquals(expected, result);
    }
    @Test
    public void testGetMinCoinsBadRequest() {
        // 构造请求，value 为空
        CoinRequest request = new CoinRequest();
        request.setTargetAmount(7.03);
        request.setValue(null);

        // 调用 Controller
        Response response = CoinApi.getMinCoins(request);

        // 验证返回状态码和错误信息
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals(Map.of("error", "denominations cannot be empty"), response.getEntity());
    }

    @Test
    public void testGetMinCoinsInvalidDenomination() {
        // 构造请求，包含无效的硬币面值
        CoinRequest request = new CoinRequest();
        request.setTargetAmount(7.03);
        request.setValue(Arrays.asList(-1.0, 0.5, 1.0));

        // 调用 Controller
        Response response = CoinApi.getMinCoins(request);

        // 验证返回状态码和错误信息
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals(Map.of("error", "denominations cannot be minus or zero"), response.getEntity());
    }
}
