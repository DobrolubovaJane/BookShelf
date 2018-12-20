package com.bookshelf.mapper;
import com.bookshelf.exception.BadRequestException;
import io.swagger.model.ReadersListModel;

import com.bookshelf.entity.Reader;
import io.swagger.model.ReaderModel;
import io.swagger.model.AddReaderRequest;
import io.swagger.model.UpdateReaderRequest;

import java.util.List;
import java.util.stream.Collectors;

public class ReaderMapper {
    private ReaderMapper() {}


    public static Reader mapAddReaderRequestToReader(AddReaderRequest request) {
        String name = request.getName();

        if (name == null || name.isEmpty()) {
            throw new BadRequestException("name is required");
        }

        Reader reader = new Reader();
        reader.setName(name);
        return reader;
    }

    public static ReaderModel mapReaderToReaderModel(Reader reader) {
        ReaderModel readerModel = new ReaderModel();
        readerModel.setId(reader.getId());
        readerModel.setName(reader.getName());
        return readerModel;
    }

    public static ReadersListModel mapReadersToReadersListModel(List<Reader> readers) {
        ReadersListModel readersListModel = new ReadersListModel();
        List<ReaderModel> bookModels = readers.stream().map(reader -> {
            ReaderModel readerModel = new ReaderModel();
            readerModel.setId(reader.getId());
            readerModel.setName(reader.getName());
            return readerModel;
        }).collect(Collectors.toList());
        readersListModel.setTotal(bookModels.size());
        readersListModel.setItems(bookModels);
        return readersListModel;
    }

    public static Reader mapUpdateReaderRequestToReader(Reader reader, UpdateReaderRequest request) {
        String name = request.getName();

        if (name == null || name.isEmpty()) {
            throw new BadRequestException("name is required");
        }

        reader.setName(name);
        return reader;
    }
}
