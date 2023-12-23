package com.example.cloudcomputing2023.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cloudcomputing2023.mapper.PaymentMapper;
import com.example.cloudcomputing2023.pojo.Payment;
import com.example.cloudcomputing2023.service.IPaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {
}
