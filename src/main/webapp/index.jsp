<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Welcome to the KOIN Marketplace!</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-xs-offset-2">
                    <h3>Ether/KOIN Exchange</h3>
                    <form class="form-horizontal" id="transfer" action="RequestServlet" method="post">
                        <div class="form-group">
                            <label for="address" class="col-sm-2 control-label">Ethereum Wallet Address</label>
                            <div class="col-sm-3 input-group">
                                <span class="input-group-addon glyphicon glyphicon-user"></span>
                                <input class="form-control" id="address" type="address" name="address" required placeholder="<0x...>"><br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="koin" class="col-sm-2 control-label">Koin</label>
                            <div class="col-sm-3 input-group">
                                <span class="input-group-addon glyphicon glyphicon-send"></span>
                                <input class="form-control" id="koin" type="koin" name="koin" required placeholder="Enter amount of KOIN"><br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">Password</label>
                            <div class="col-sm-3 input-group">
                                <span class="input-group-addon glyphicon glyphicon-send"></span>
                                <input class="form-control" id="password" type="password" name="password" required placeholder="Enter password"><br>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-10 col-sm-push-2">
                                <input class="btn btn-info" type="submit" name="submit" value="Submit">
                            </div>
                        </div>
                    </form>
                    ${symbol}
                </div>
            </div>
        </div>
    </body>
</html>