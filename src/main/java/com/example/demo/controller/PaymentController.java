package com.example.demo.controller;

import com.example.demo.configs.VnPayConfigs;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.PaymentRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Payment;
import com.example.demo.response.CustomStatusResponse;
import com.example.demo.service.PaymentService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomStatusResponse customStatusResponse;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PatientRepository patientRepository;


    @CrossOrigin
    @GetMapping("/payment/list")
    public ResponseEntity<?> getList() {
        try {
            var data = paymentService.getAllPayments();
            if (data.stream().count() > 0) {
                return customStatusResponse.OK200("Get list success", data);

            } else {
                return customStatusResponse.NOTFOUND404("list not found");
            }
        } catch (Exception ex) {

            return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping("/payment/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id) {
        try {
            Payment payment = paymentService.getPaymentById(id);
            if (payment == null) {
                return customStatusResponse.NOTFOUND404("list not found");
            } else {
                return customStatusResponse.OK200("Get success", payment);
            }
        } catch (Exception ex) {

            return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
        }
    }

    @CrossOrigin
    @PostMapping("/payment/create")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        try {
            Payment createdPayment = paymentService.createPayment(payment);
            if (createdPayment != null) {
                return customStatusResponse.CREATED201("Create success", createdPayment);
            } else {
                return customStatusResponse.BADREQUEST400("Create fail");
            }

        } catch (Exception ex) {

            return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
        }
    }

    @CrossOrigin
    @PutMapping("/update-payment/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Integer id, @RequestBody Payment payment) {
        try {
            Payment updatePayment = paymentService.updatePayment(id, payment);
            if (updatePayment != null) {
                return customStatusResponse.OK200("Get success", payment);
            } else {
                return customStatusResponse.NOTFOUND404("list not found");
            }

        } catch (Exception ex) {

            return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping("/delete-payment/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            boolean checkPayment = paymentService.deletePayment(id);
            if (checkPayment) {
                return customStatusResponse.OK200("Delete success");
            } else {
                return customStatusResponse.BADREQUEST400("Delete fail");
            }
        } catch (Exception ex) {
            return customStatusResponse.INTERNALSERVERERROR500(ex.getMessage());
        }
    }

//    @CrossOrigin
//    @GetMapping("/payment-callback")
//    public void paymentCallback(@RequestParam Map<String, String> queryParams, HttpServletResponse response) throws IOException {
//        String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
//        String contractId = queryParams.get("id");
//        String registerServiceId = queryParams.get("registerServiceId");
//        if(contractId!= null && !contractId.equals("")) {
//            if ("00".equals(vnp_ResponseCode)) {
//                // Giao dịch thành công
//                // Thực hiện các xử lý cần thiết, ví dụ: cập nhật CSDL
//                Payment payment = PaymentRepository.(Integer.parseInt(queryParams.get("contractId")))
//                        .orElseThrow(() -> new ChangeSetPersister.NotFoundException("Không tồn tại hợp đồng này của sinh viên"));
//                payment.setStatus(true);
//                paymentRepository.save(payment);
//                response.sendRedirect("http://localhost:4200/info-student");
//            } else {
//                // Giao dịch thất bại
//                // Thực hiện các xử lý cần thiết, ví dụ: không cập nhật CSDL\
//                response.sendRedirect("http://localhost:4200/payment-failed");
//
//            }
//        }
//        if(registerServiceId!= null && !registerServiceId.equals("")) {
//            if ("00".equals(vnp_ResponseCode)) {
//                // Giao dịch thành công
//                // Thực hiện các xử lý cần thiết, ví dụ: cập nhật CSDL
//                RegisterServices registerServices = registerServicesRepository.findById(Integer.parseInt(queryParams.get("registerServiceId")))
//                        .orElseThrow(() -> new NotFoundException("Không tồn tại dịch vụ này của sinh viên"));
//                registerServices.setStatus(1);
//                registerServicesRepository.save(registerServices);
//                response.sendRedirect("http://localhost:4200/info-student");
//            } else {
//                // Giao dịch thất bại
//                // Thực hiện các xử lý cần thiết, ví dụ: không cập nhật CSDL\
//                response.sendRedirect("http://localhost:4200/payment-failed");
//
//            }
//        }
//    }
    @PostMapping("/vnPay")
    public String getPay(@PathParam("price") double price, @PathParam("id") long id) throws UnsupportedEncodingException {

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = (long) (price * 100);
        String bankCode = "NCB";


        String vnp_TxnRef = VnPayConfigs.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = VnPayConfigs.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VnPayConfigs.vnp_Version);
        vnp_Params.put("vnp_Command", VnPayConfigs.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VnPayConfigs.vnp_ReturnUrl);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VnPayConfigs.vnp_ReturnUrl + "?id=" + id);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);


        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VnPayConfigs.hmacSHA512(VnPayConfigs.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VnPayConfigs.vnp_PayUrl + "?" + queryUrl;
        return paymentUrl;


    }

    @GetMapping("pay-service")
    public String getPayService(@PathParam("price") double price, @PathParam("id") long id) throws UnsupportedEncodingException{
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = (long) (price * 100);
        String bankCode = "NCB";


        String vnp_TxnRef = VnPayConfigs.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = VnPayConfigs.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VnPayConfigs.vnp_Version);
        vnp_Params.put("vnp_Command", VnPayConfigs.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VnPayConfigs.vnp_ReturnUrl);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VnPayConfigs.vnp_ReturnUrl + "?id=" + id);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);


        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VnPayConfigs.hmacSHA512(VnPayConfigs.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VnPayConfigs.vnp_PayUrl + "?" + queryUrl;
        return paymentUrl;
    }




}


