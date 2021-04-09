import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:movie_app/routes/routes.dart';

class WelcomePage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(children: <Widget>[
        SizedBox(
          height: 100,
        ),
        Center(
          child: Image(
            image: AssetImage('assets/app_cinema.png'),
          ),
        ),
        Text(
          'Start now',
          style: TextStyle(fontWeight: FontWeight.bold, fontSize: 25, color: Color.fromRGBO(41, 50, 65, 1)),
        ),
        SizedBox(
          height: 10,
        ),
        Text(
          'Create an account and explore the movies world',
          textAlign: TextAlign.center,
          style: TextStyle(
            fontSize: 15,
            color: Color.fromRGBO(41, 50, 65, 1),
          ),
        ),
        SizedBox(height: 30,),
        Padding(
          padding: const EdgeInsets.all(16.0),
          child: SizedBox(
            width: double.infinity,
            height: 40,
            child: ElevatedButton(
              onPressed: () async {
                Navigator.pushNamed(context, AppRoutes.signup);
              },
              child: Text(
                  'Getting started',
                  style: TextStyle(
                    fontSize: 16,
                  )
              ),

              style: ElevatedButton.styleFrom(
                primary: Color.fromRGBO(41, 50, 65, 1),
              ),
            ),
          ),
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Already have an account? ',
              style: TextStyle(
                color: Color.fromRGBO(41, 50, 65, 1),
                fontSize: 15,
              ),
            ),
            GestureDetector(
              onTap: (){
                Navigator.pushNamed(context, AppRoutes.login);
              },
              child: Text(
                'Log in',
                style: TextStyle(
                  fontWeight: FontWeight.bold,//
                  color: Color.fromRGBO(238, 108, 77, 1),
                  fontSize: 15,
                ),
              ),
            ),
          ],
        ),

      ]),
    );
  }
}