import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:movie_app/routes/routes.dart';

// ignore: must_be_immutable
class SignUpPage extends StatelessWidget {

  final _formKey = GlobalKey<FormState>();

  String email = '';
  String password = '';
  String displayName = '';

  Widget build(BuildContext context) {
    return Scaffold(
        body: Form(
          key: _formKey,
          child: SingleChildScrollView(
            child: Center(
              child: Column(children: <Widget>[
                SizedBox(height: 70,),
                Text(
                  'Sign Up',
                  style: TextStyle(fontWeight: FontWeight.bold,
                      fontSize: 25,
                      color: Color.fromRGBO(61, 90, 128, 1)),
                ),
                SizedBox(height: 10,),
                Text(
                  'Complete your information below',
                  style: TextStyle(
                      fontSize: 20, color: Color.fromRGBO(61, 90, 128, 1)),
                ),
                SizedBox(height: 20,),
                Container(
                    child: Padding(
                        padding: const EdgeInsets.symmetric(
                            horizontal: 25.0, vertical: 10),
                        child: TextFormField(
                            validator: (val) =>
                            val.isEmpty
                                ? 'Enter your name'
                                : null,
                            onChanged: (val) {
                              displayName = val;
                            },
                            decoration: InputDecoration(
                              labelText: 'Name and surname',
                              labelStyle: TextStyle(
                                  color: Colors.blueGrey
                              ),
                              enabledBorder: new OutlineInputBorder(
                                  borderSide: new BorderSide(
                                      color: Color.fromRGBO(61, 90, 128, 1)
                                  )
                              ),
                              focusedBorder: new OutlineInputBorder(
                                  borderSide: new BorderSide(
                                      color: Color.fromRGBO(61, 90, 128, 1)
                                  )
                              ),
                            )
                        )
                    )
                ),
                Container(
                    child: Padding(
                        padding: const EdgeInsets.symmetric(
                            horizontal: 25.0, vertical: 10),
                        child: TextFormField(
                            validator: (val) =>
                            val.isEmpty
                                ? 'Enter an email'
                                : null,
                            onChanged: (val) {
                              email = val;
                            },
                            decoration: InputDecoration(
                              labelText: 'E-mail',
                              labelStyle: TextStyle(
                                  color: Colors.blueGrey
                              ),
                              enabledBorder: new OutlineInputBorder(
                                  borderSide: new BorderSide(
                                      color: Color.fromRGBO(61, 90, 128, 1)
                                  )
                              ),
                              focusedBorder: new OutlineInputBorder(
                                  borderSide: new BorderSide(
                                      color: Color.fromRGBO(61, 90, 128, 1)
                                  )
                              ),
                            )
                        )
                    )
                ),
                Container(
                    child: Padding(
                        padding: const EdgeInsets.symmetric(
                            horizontal: 25.0, vertical: 10),
                        child: TextFormField(
                            obscureText: true,
                            validator: (val) =>
                            val.length < 6
                                ? 'Enter a password 6+ chars long'
                                : null,
                            onChanged: (val) {
                              password = val;
                            },
                            decoration: InputDecoration(
                                labelText: 'Password',
                                labelStyle: TextStyle(
                                    color: Colors.blueGrey
                                ),
                                enabledBorder: new OutlineInputBorder(
                                    borderSide: new BorderSide(
                                        color: Color.fromRGBO(61, 90, 128, 1)
                                    )
                                ),
                                focusedBorder: new OutlineInputBorder(
                                    borderSide: new BorderSide(
                                        color: Color.fromRGBO(61, 90, 128, 1)
                                    )
                                )
                            )
                        )
                    )
                ),
                Padding(
                  padding: const EdgeInsets.symmetric(
                      horizontal: 25.0, vertical: 50),
                  child: SizedBox(
                    width: double.infinity,
                    height: 45,
                    child: ElevatedButton(
                        onPressed: () async {},
                        style: ElevatedButton.styleFrom(
                            primary: Color.fromRGBO(238, 108, 77, 1)
                        ),
                        child: Text(
                            'Sign Up',
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 16,
                            )
                        )
                    ),
                  ),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Text(
                      'Already have an account? ',
                      style: TextStyle(
                        color: Color.fromRGBO(29, 52, 97, 1),
                        fontSize: 15,
                      ),
                    ),
                    GestureDetector(
                      onTap: () {
                        Navigator.pushNamed(context, AppRoutes.login);
                      },
                      child: Text(
                        'Log In',
                        style: TextStyle(
                          fontWeight: FontWeight.bold, //
                          color: Color.fromRGBO(238, 108, 77, 1),
                          fontSize: 15,
                        ),
                      ),
                    ),
                  ],
                ),
              ]),
            ),
          ),
        )
    );
  }
}