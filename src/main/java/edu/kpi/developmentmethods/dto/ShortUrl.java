package edu.kpi.developmentmethods.dto;

// DTO (data-transfer object) — объект, который используется для передачи данных
// В даном примере мы используем экспериментальную в Java15 возможность — record classes,
// чтобы сократить количество написаного кода
public record Example (String title, int rating) {}


}
