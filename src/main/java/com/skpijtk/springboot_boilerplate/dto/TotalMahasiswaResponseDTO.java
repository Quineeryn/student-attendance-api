package com.skpijtk.springboot_boilerplate.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TotalMahasiswaResponseDTO {

    private long totalMahasiswa;

    public TotalMahasiswaResponseDTO(long totalMahasiswa) {
        this.totalMahasiswa = totalMahasiswa;
    }

}