package org.bamappli.ticketglob.Controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.Ticket;
import org.bamappli.ticketglob.Services.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apprenant/tickets")
@Getter
@Setter
@AllArgsConstructor
public class TicketController {
    TicketService ticketService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Ticket create(@RequestBody Ticket ticket) {
        return ticketService.creer(ticket);
    }

    @GetMapping
    public List<Ticket> getAll() {
        return ticketService.tout();
    }
    @GetMapping("/{id}")
    public Optional<Ticket> getOne(@PathVariable Integer id) {
        return ticketService.unTicket(id);
    }

    @PutMapping("/{id}")
    public Ticket update(@PathVariable Integer id, @RequestBody Ticket ticket) {
        return ticketService.misAjour(id, ticket);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        ticketService.effacer(id);
    }

}
