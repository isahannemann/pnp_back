package com.peaktech.pnp.model.output.photo;

import java.io.IOException;

public interface PhotoOutputStrategy {
    PhotoOutput getPhoto(String filename) throws IOException;
}
