package com.urlShortner.Application;

import com.urlShortner.Application.Requests.Request;
import com.urlShortner.Application.Requests.RequestRepository;
import com.urlShortner.Application.URLs.Url;
import com.urlShortner.Application.URLs.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class MainController {

    final long value = 1000L;

    @Autowired
    private UrlRepository urLsRepository;
    @Autowired
    private RequestRepository requestsRepository;

    @GetMapping("/{pathVariable}")
    public ResponseEntity<?> redirect(@PathVariable("pathVariable")String link, HttpServletRequest request) {
        if(link != null) {
            Url result = urLsRepository.findByShortURL(link);
            if (result != null) {
                if (result.getExpiresAt() > System.currentTimeMillis() / value) {
                    if (result.getVisitorLimit() == 0) {
                        String orig_url = result.getOrigURL();
                        if (!orig_url.startsWith("http://") && !orig_url.startsWith("https://"))
                            orig_url = "http://" + orig_url;

                        // Increment view count
                        result.setVisitorCount(result.getVisitorCount() + 1);
                        result = urLsRepository.save(result);

                        // Get Referer
                        String referer = request.getHeader("referer");

                        // Record the new request.
                        addNewRequest(result.getId(),  request.getRemoteAddr(), referer);

                        HttpHeaders headers = new HttpHeaders();
                        headers.add("Location", orig_url);
                        return new ResponseEntity<String>(headers, HttpStatus.PERMANENT_REDIRECT);
                    } else {
                        if (result.getVisitorLimit() > result.getVisitorCount()) {
                            String orig_url = result.getOrigURL();
                            if (!orig_url.startsWith("http://") && !orig_url.startsWith("https://"))
                                orig_url = "http://" + orig_url;

                            // Increment view count.
                            result.setVisitorCount(result.getVisitorCount() + 1);
                            result = urLsRepository.save(result);

                            // Get Referer
                            String referer = request.getHeader("referer");

                            // Record the new request.
                            addNewRequest(result.getId(),  request.getRemoteAddr(), referer);

                            HttpHeaders headers = new HttpHeaders();
                            headers.add("Location", orig_url);
                            return new ResponseEntity<String>(headers,HttpStatus.PERMANENT_REDIRECT);
                        } else {
                            return new ResponseEntity<>("Link has reached the visitor limit.", HttpStatus.NOT_FOUND);
                        }
                    }
                } else {
                    return new ResponseEntity<>("Link has been expired.", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Link does not exist.", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Hello.", HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> hello() {
        return new ResponseEntity<>("API is running.", HttpStatus.OK);
    }

    boolean addNewRequest(UUID url_id, String request_ip, String request_referrer) {
        Request new_request = new Request();
        new_request.setUrlID(url_id);
        new_request.setRequestIP(request_ip);
        new_request.setRequestReferrer(request_referrer);
        new_request.setCreatedAt(System.currentTimeMillis() / 1000L);

        // Obtaining country code.
//        HttpGet request = new HttpGet("http://api.ipstack.com/" + request_ip + "?access_key=ff5cc578f42088401b53727c61d7f066");
//        try (CloseableHttpClient httpClient = HttpClients.createDefault();
//             CloseableHttpResponse response = httpClient.execute(request)) {

//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                // return it as a String
//                String country_response = EntityUtils.toString(entity);
//                country_response = StringUtils.substringBetween(country_response, "\"country_name\":\"", "\"");
//                new_request.setCountryCode(Objects.requireNonNullElse(country_response, "N/A"));
//            } else {
//                new_request.setCountryCode("N/A");
//            }
//        } catch (Exception e) {
//            new_request.setCountryCode("N/A");
//        }

        try {
            requestsRepository.save(new_request);
        } catch (DataIntegrityViolationException e) {
            return false;
        }

        return true;

    }
}
