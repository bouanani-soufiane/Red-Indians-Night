package dev.codex.redindiansnight.Event.Application.Services;

import dev.codex.redindiansnight.Common.Contracts.CrudService;
import dev.codex.redindiansnight.Event.Application.Dtos.Requests.EventRequest;
import dev.codex.redindiansnight.Event.Domain.Entities.Event;

public interface EventService extends CrudService<Event, Long, EventRequest> {
}
