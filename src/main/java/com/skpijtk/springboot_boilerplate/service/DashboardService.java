// File: src/main/java/com/skpijtk/springboot_boilerplate/service/DashboardService.java
package com.skpijtk.springboot_boilerplate.service;

import com.skpijtk.springboot_boilerplate.dto.ResumeCheckinResponseDTO;
import com.skpijtk.springboot_boilerplate.dto.TotalMahasiswaResponseDTO;

public interface DashboardService {

    /**
     * Mendapatkan jumlah total mahasiswa yang terdaftar.
     * @return DTO yang berisi jumlah total mahasiswa.
     */
    TotalMahasiswaResponseDTO getTotalMahasiswa();

    /**
     * Mendapatkan ringkasan data check-in untuk hari ini.
     * @return DTO yang berisi ringkasan check-in.
     */
    ResumeCheckinResponseDTO getCheckinResume();
}