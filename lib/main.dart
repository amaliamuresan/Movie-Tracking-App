import 'package:flutter/material.dart';
import 'package:movie_app/presentation/screens/welcome_page.dart';
import 'package:movie_app/routes/routes.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      routes: AppRoutes.routes,
      home: WelcomePage(),
    );
  }
}



