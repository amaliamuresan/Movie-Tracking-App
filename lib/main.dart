import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/data/api.dart';
import 'package:movie_app/epics/app_epics.dart';
import 'package:movie_app/models/states/app_state.dart';
import 'package:movie_app/presentation/screens/welcome_page.dart';
import 'package:movie_app/reducer/reducer.dart';
import 'package:movie_app/routes/routes.dart';
import 'package:http/http.dart';
import 'package:redux/redux.dart';
import 'package:redux_epics/redux_epics.dart';

void main() {
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
  );
  runApp(MyApp());
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



