package dev.codex.redindiansnight.Event.Application.Services;

import dev.codex.redindiansnight.Common.Contracts.CrudService;
import dev.codex.redindiansnight.Event.Application.Dtos.Requests.EventRequest;
import dev.codex.redindiansnight.Event.Domain.Entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService extends CrudService<Event, Long, EventRequest> {
    Page<Event> findAll(int pageNum, int pageSize);
}
