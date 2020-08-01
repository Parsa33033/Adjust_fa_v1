import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './tutorial.reducer';
import { ITutorial } from 'app/shared/model/tutorial.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITutorialProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Tutorial = (props: ITutorialProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { tutorialList, match, loading } = props;
  return (
    <div>
      <h2 id="tutorial-heading">
        <Translate contentKey="adjustApp.tutorial.home.title">Tutorials</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.tutorial.home.createLabel">Create new Tutorial</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {tutorialList && tutorialList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.tutorial.title">Title</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.tutorial.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.tutorial.text">Text</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.tutorial.thumbnail">Thumbnail</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.tutorial.tokenPrice">Token Price</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.tutorial.adjustTutorialId">Adjust Tutorial Id</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.tutorial.video">Video</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.tutorial.client">Client</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tutorialList.map((tutorial, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${tutorial.id}`} color="link" size="sm">
                      {tutorial.id}
                    </Button>
                  </td>
                  <td>{tutorial.title}</td>
                  <td>{tutorial.description}</td>
                  <td>{tutorial.text}</td>
                  <td>
                    {tutorial.thumbnail ? (
                      <div>
                        {tutorial.thumbnailContentType ? (
                          <a onClick={openFile(tutorial.thumbnailContentType, tutorial.thumbnail)}>
                            <img src={`data:${tutorial.thumbnailContentType};base64,${tutorial.thumbnail}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {tutorial.thumbnailContentType}, {byteSize(tutorial.thumbnail)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{tutorial.tokenPrice}</td>
                  <td>{tutorial.adjustTutorialId}</td>
                  <td>{tutorial.videoId ? <Link to={`tutorial-video/${tutorial.videoId}`}>{tutorial.videoId}</Link> : ''}</td>
                  <td>{tutorial.clientId ? <Link to={`adjust-client/${tutorial.clientId}`}>{tutorial.clientId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tutorial.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tutorial.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tutorial.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adjustApp.tutorial.home.notFound">No Tutorials found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ tutorial }: IRootState) => ({
  tutorialList: tutorial.entities,
  loading: tutorial.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Tutorial);
