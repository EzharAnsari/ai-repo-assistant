package ez.backend.services.ai;

import java.util.List;

import ez.backend.dto.CitationDto;

public record RetrievedContext(
        List<CitationDto> citations,
        String contextText) {
}