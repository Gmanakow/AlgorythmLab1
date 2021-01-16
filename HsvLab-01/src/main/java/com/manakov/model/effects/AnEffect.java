package com.manakov.model.effects;

import java.awt.image.BufferedImage;

public interface AnEffect {
    int[] applyFilter(BufferedImage source, int x, int y);
}
