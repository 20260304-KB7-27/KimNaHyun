package org.scoula.database.trevelapp.src.main.java.org.scoula.travel.app;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItem {
    String title;
    Runnable command;
}