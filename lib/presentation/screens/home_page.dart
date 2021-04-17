import 'package:built_collection/built_collection.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:movie_app/actions/get_movies.dart';
import 'package:movie_app/containers/movies_container.dart';
import 'package:movie_app/models/movie.dart';
import 'package:movie_app/models/states/app_state.dart';

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color.fromRGBO(29, 52, 97, 1),
      ),
      body: SingleChildScrollView(
        child: MoviesContainer(
          builder: (BuildContext context, BuiltList<Movie> movies) {
            return Column(
              children: <Widget>[
                SizedBox(
                  height: 16,
                ),
                GridView.builder(
                  itemCount: movies.length,
                  physics: const NeverScrollableScrollPhysics(),
                  shrinkWrap: true,
                  gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                    childAspectRatio: 0.75,
                    crossAxisCount: 3,
                    crossAxisSpacing: 4.0,
                    mainAxisSpacing: 16.0,
                  ),
                  itemBuilder: (BuildContext context, int index) {
                    return Container(
                      child: Image(
                        image: NetworkImage(movies[index].poster_path),
                      ),
                    );
                  },
                ),
                GestureDetector(
                  onTap: () {
                    StoreProvider.of<AppState>(context)
                        .dispatch(const GetMovies());
                  },
                  child: Image(
                    width: 40,
                    height: 40,
                    image: AssetImage('assets/more.png'),
                  ),
                )
              ],
            );
          },
        ),
      ),
    );
  }
}
