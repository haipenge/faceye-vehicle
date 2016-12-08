package com.faceye.component.vehicle.service;

import java.io.OutputStream;

import org.springframework.data.domain.Page;

import com.faceye.component.vehicle.entity.LicensePlate;

public interface ExcelService {

	void export(Page<LicensePlate> licensePlates, OutputStream os);

}