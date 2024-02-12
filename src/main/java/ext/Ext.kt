package ext

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

// Make it easier to throw a ResponseStatusException
operator fun HttpStatus.invoke(message: String? = null): Nothing = throw ResponseStatusException(this, message ?: this.reasonPhrase)