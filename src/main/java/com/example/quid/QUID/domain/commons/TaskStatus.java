package com.example.quid.QUID.domain.commons;

public enum TaskStatus {
    PENDIENTE("Pendiente"),
    COMPLETADA("Completada");
    public final String value;

    TaskStatus(String value) {
        this.value = value;
    }
}
