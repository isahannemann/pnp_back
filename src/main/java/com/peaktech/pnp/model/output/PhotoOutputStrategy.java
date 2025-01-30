package com.peaktech.pnp.model.output;

import java.io.IOException;

public interface PhotoOutputStrategy {
    PhotoOutput getPhoto(String filename) throws IOException;
}

