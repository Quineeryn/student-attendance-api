// File: src/main/java/com/skpijtk/springboot_boilerplate/service/AppSettingsService.java
package com.skpijtk.springboot_boilerplate.service;

import com.skpijtk.springboot_boilerplate.dto.AppSettingsDTO;

public interface AppSettingsService {

    /**
     * Mengambil pengaturan aplikasi saat ini.
     * @return DTO yang berisi detail pengaturan.
     */
    AppSettingsDTO getAppSettings();

    /**
     * Memperbarui pengaturan aplikasi.
     * @param appSettingsDTO DTO yang berisi nilai pengaturan baru.
     * @return DTO yang berisi pengaturan setelah berhasil diperbarui.
     */
    AppSettingsDTO updateAppSettings(AppSettingsDTO appSettingsDTO);
}