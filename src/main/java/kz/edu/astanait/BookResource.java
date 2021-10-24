package kz.edu.astanait;

import model.Book;
import repositories.BookRepository;
import repositories.interfaces.IBookRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/books")
public class BookResource {
    private IBookRepository bookRepo = new BookRepository();
    @GET
    @Path("{id}")
    @Produces (MediaType.APPLICATION_JSON)
    public Response get (@PathParam("id") int id) {
        Book book = bookRepo.findBookById(id);
        if (book != null) {
            return Response.ok (book, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
