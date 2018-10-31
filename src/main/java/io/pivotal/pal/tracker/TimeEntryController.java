package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(timeEntry);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {


        TimeEntry foundTimeEntry = timeEntryRepository.find(timeEntryId);


        return ResponseEntity
                .status( foundTimeEntry == null ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                .body(foundTimeEntry);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(timeEntryRepository.list());
    }

    @PutMapping("/time-entries/{id}")

    public ResponseEntity update(@PathVariable("id") long timeEntryId,@RequestBody TimeEntry expected) {


        TimeEntry update = timeEntryRepository.update(timeEntryId, expected);

        return ResponseEntity
                .status( update == null ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                .body(update);

    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable("id") long timeEntryId) {

        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }
}
