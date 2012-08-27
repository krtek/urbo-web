package api

import org.junit.Test
import cz.urbo.jobs.MailJob
import org.junit.Before
import com.naleid.grails.MarkdownService

class MailJobIntegrationTest extends GroovyTestCase {
    def MailJob mailJob
    def MarkdownService markdownService

    @Before
    void setup() {
        mailJob = new MailJob()
        markdownService = new MarkdownService()
    }

    @Test
    void formatBody() {
        def body = mailJob.formatBody("Rozsypaná popelnice", "Všude", "Matěj Brouček")
        assertTrue(body.contains("Rozsypaná popelnice"))
        assertTrue(body.contains("Všude"))
        assertTrue(body.contains("Matěj Brouček"))
    }

}
