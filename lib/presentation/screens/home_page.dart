import 'package:built_collection/built_collection.dart';
import 'package:flutter/material.dart';
import 'package:movie_app/containers/movies_container.dart';
import 'package:movie_app/models/movie.dart';

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
                GridView.builder(
                  itemCount: movies.length,
                  physics: const NeverScrollableScrollPhysics(),
                  shrinkWrap: true,
                  padding: EdgeInsets.all(1),
                  gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                      crossAxisCount: 3,
                      crossAxisSpacing: 2.0,
                      mainAxisSpacing: 2.0,
                  ),
                  itemBuilder: (BuildContext context, int index){
                   // return Image.network(movies[index].poster_path);
                    return Container(
                      child: Image(
                        image: NetworkImage(movies[index].poster_path),
                      ),
                    );
                  },
                ),
              ],
            );
          },
        ),
      ),
    );
  }
}
