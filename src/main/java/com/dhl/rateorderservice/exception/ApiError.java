package com.dhl.rateorderservice.exception;

public record ApiError(int status, String message) { }
