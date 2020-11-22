package am.gevorg.scope.controller;

import am.gevorg.scope.dto.req.LogCreateRequest;
import am.gevorg.scope.exception.ModelNotFoundException;
import am.gevorg.scope.model.Log;
import am.gevorg.scope.security.AuthSecurityService;
import am.gevorg.scope.service.LogService;
import am.gevorg.scope.util.LogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;
    private final LogUtil logUtil;
    private final AuthSecurityService authSecurityService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody LogCreateRequest logCreateRequest) {
        Log log = logService.create(logCreateRequest);
        return ResponseEntity.ok(logUtil.logToDto(log));
    }

        @RequestMapping(value = "/all-logs", method = RequestMethod.GET)
    public ResponseEntity<List<Log>> getAllLog() {
        return ResponseEntity.ok(logService.getAllLogs());
    }

    @RequestMapping(value = "/logs-per-user", method = RequestMethod.GET)
    public ResponseEntity<List<Log>> getAllLogsPerUser() {
        return ResponseEntity.ok(logService.getAllLogsPerUser(authSecurityService.getCurrentUser()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Log> findById(@PathVariable int id) {
        return ResponseEntity.ok(logService.findById(id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        logService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
