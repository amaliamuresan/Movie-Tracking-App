import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/actions/get_movies.dart';
import 'package:movie_app/data/api.dart';
import 'package:movie_app/epics/app_epics.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:movie_app/presentation/screens/welcome_page.dart';
import 'package:movie_app/reducer/reducer.dart';
import 'package:movie_app/routes/routes.dart';
import 'package:http/http.dart';
import 'package:redux/redux.dart';
import 'package:redux_epics/redux_epics.dart';

class MyHttpOverrides extends HttpOverrides{
  @override
  HttpClient createHttpClient(SecurityContext context){
    return super.createHttpClient(context)
      ..badCertificateCallback = (X509Certificate cert, String host, int port)=> true;
  }
}
void main() {
  HttpOverrides.global = new MyHttpOverrides();
  WidgetsFlutterBinding.ensureInitialized();
  final Client client = Client();
  final AppApi api = AppApi(client: client);
  final AppState initialState = AppState.initialState();
  final AppEpics appEpics = AppEpics(api: api);
  final Store<AppState> store = Store<AppState>(
    reducer,
    initialState: initialState,
    middleware: <Middleware<AppState>>[
      EpicMiddleware<AppState>(appEpics.epics),
    ],
  )..dispatch(GetMovies());
  runApp(MyApp(store: store,));
}

class MyApp extends StatelessWidget {
  const MyApp({Key key, @required this.store}) : super(key: key);

  final Store<AppState> store;

  @override
  Widget build(BuildContext context) {
    return StoreProvider<AppState>(
      store: store,
        child: MaterialApp(
        routes: AppRoutes.routes,
        home : WelcomePage(),
        ),
    );
  }
}



