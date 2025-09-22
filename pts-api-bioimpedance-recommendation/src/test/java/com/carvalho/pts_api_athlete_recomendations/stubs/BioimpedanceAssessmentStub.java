package com.carvalho.pts_api_athlete_recomendations.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BioimpedanceAssessmentStub {

    public static void stubBioimpedanceAssessmentCall(Long bioimpedanceId){
        stubFor(get(urlEqualTo("/api/v1/bioimpedance-assessment?bioimpedanceId=" + bioimpedanceId))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));
    }

}
