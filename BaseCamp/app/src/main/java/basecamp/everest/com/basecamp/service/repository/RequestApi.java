package basecamp.everest.com.basecamp.service.repository;

import basecamp.everest.com.basecamp.service.model.MakesResponse;
import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface RequestApi {

    @GET("vehicles/GetAllMakes")
    Observable<MakesResponse> fetchMakes(@Query("format") String format);
}
