package com.revature.main;

import com.revature.controller.BankController;
import com.revature.controller.ExceptionController;
import com.revature.controller.Controller;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO add transfer method
// TODO 1 Add logging for exceptions and also create examples in Postman
// TODO Log all methods when called
public class Driver {
    private static Logger logger = LoggerFactory.getLogger(Driver.class);

    public static void main(String[] args) {
        logger.info("Main called, app beginning...");
        Javalin app = Javalin.create();

        app.before(ctx -> {
            logger.info(ctx.method() + " request received for " + ctx.path());
        });

        map(app, new BankController(), new ExceptionController());

        app.start(7070);
    }

    public static void map(Javalin app, Controller... controllers) {
        for(Controller c : controllers) {
            c.mapEndpoints(app);
        }
    }
}
