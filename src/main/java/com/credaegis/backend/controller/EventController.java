package com.credaegis.backend.controller;

import com.credaegis.backend.constant.Constants;
import com.credaegis.backend.configuration.security.principal.CustomUser;
import com.credaegis.backend.http.request.EventCreationRequest;
import com.credaegis.backend.http.request.EventModificationRequest;
import com.credaegis.backend.http.response.api.CustomApiResponse;
import com.credaegis.backend.http.response.custom.EventSearchResponse;
import com.credaegis.backend.service.EventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.ROUTEV1 + "/event-control")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;


    @GetMapping(path="/search")
    public final ResponseEntity<CustomApiResponse<List<EventSearchResponse>>> searchEvents(@RequestParam String name,
                                                                                           @AuthenticationPrincipal CustomUser customUser) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new CustomApiResponse<>(eventService.searchEvents(name, customUser.getOrganizationId()), "Events fetched", true)
        );
    }

    @PostMapping(path = "/create")
    public ResponseEntity<CustomApiResponse<Void>> createEvent(@RequestBody @Valid EventCreationRequest eventCreationRequest,
                                                               @AuthenticationPrincipal CustomUser customUser) {


        eventService.createEvent(eventCreationRequest, customUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomApiResponse<>(null,
                "Event Successfully created", true));

    }

    @PutMapping(path = "/activate/{id}")
    public ResponseEntity<CustomApiResponse<Void>> activateEvent(@PathVariable String id,
                                                                 @AuthenticationPrincipal CustomUser customUser) {

        eventService.activateEvent(id, customUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new CustomApiResponse<>(null, "Event Successfully activated", true)
        );
    }

    @PutMapping(path = "/deactivate/{id}")
    public ResponseEntity<CustomApiResponse<Void>> deactivateEvent(@PathVariable String id,
                                                                   @AuthenticationPrincipal CustomUser customUser) {
        eventService.deactivateEvent(id, customUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new CustomApiResponse<>(null, "Event Successfully deactivated", true)
        );
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<CustomApiResponse<Void>> updateEvent(@PathVariable  String id, @RequestBody @Valid
                                                               EventModificationRequest eventModificationRequest,
                                                               @AuthenticationPrincipal CustomUser customUser) {
        eventService.updateEvent(eventModificationRequest, customUser.getId(),
                id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new CustomApiResponse<>(null, "Event Successfully updated", true)
        );
    }
}
