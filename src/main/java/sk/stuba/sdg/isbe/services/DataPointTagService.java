package sk.stuba.sdg.isbe.services;

import org.springframework.web.bind.annotation.PathVariable;
import sk.stuba.sdg.isbe.domain.model.DataPoint;
import sk.stuba.sdg.isbe.domain.model.DataPointTag;
import sk.stuba.sdg.isbe.domain.model.StoredData;

import java.util.List;

public interface DataPointTagService {

    DataPointTag createDataPointTag(DataPointTag dataPointTag);

    List<DataPointTag> getDataPointTags();

    DataPointTag getDataPointTagById(String dataPointTagId);

    List<StoredData> getStoredData(String dataPointTagId);

    List<StoredData> getStoredDataByTime(String dataPointTagId, Long startTime, Long endTime,  Long cadence);

    DataPointTag updateDataPointTag(String dataPointTagId, DataPointTag changeDataPointTag);

    DataPointTag deleteDataPointTag(String dataPointTagId);
}
