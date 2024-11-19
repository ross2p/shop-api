package ua.edu.lnu.shop_api.entity;

import lombok.ToString;

@ToString
public enum Status {
    PENDING,
    AWAITING_PAYMENT,
    DELIVERY,
    COMPLETED,
    CANSELED
}